package frame.basis.jitpack.extension.util;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class ImageUtil {

    private static byte[] data = null;
    private static byte[] rowData = null;
    private static byte[] yRawSrcBytes;
    private static byte[] uRawSrcBytes;
    private static byte[] vRawSrcBytes;
    private static byte[] nv21;
    private static final YuvToRgbConverter yuvToRgbConverter = YuvToRgbConverter.Companion.get();

    /**
     * NV21转JPEG数组
     *
     * @param nv21    数据
     * @param width   宽
     * @param height  高
     * @param quality 压缩
     * @return JPEG数组，可使用Bitmap
     */
    public static byte[] nv21ToJpeg(byte[] nv21, int width, int height, int quality) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        YuvImage yuv = new YuvImage(nv21, ImageFormat.NV21, width, height, null);
        yuv.compressToJpeg(new Rect(0, 0, width, height), quality, out);
        return out.toByteArray();
    }

    /**
     * 重置数组
     */
    @Deprecated
    public static void resetByte() {
        data = null;
        rowData = null;
    }

    public static void resetConverter() {
        yuvToRgbConverter.reset();
    }

    /**
     * Camera2 YUV420 转 NV21数据
     */
    public static byte[] yuv420toNv21Converter(Image image, boolean isMirror) {
        byte[] bytes = yuvToRgbConverter.yuvToNv21(image);
        if (isMirror) {
            Rect crop = image.getCropRect();
            int width = crop.width();
            int height = crop.height();
            return nv21Mirror(data, width, height);
        }
        return bytes;
    }

    /**
     * Camera2 YUV420 转 NV21数据
     *
     * @param image    Image
     * @param isMirror 是否镜像,前置需要，后置不需要
     * @return NV21数组
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static byte[] yuv420toNv21Dep(Image image, boolean isMirror) {
        Rect crop = image.getCropRect();
        int format = image.getFormat();
        int width = crop.width();
        int height = crop.height();
        Image.Plane[] planes = image.getPlanes();
        int dataLength = width * height * ImageFormat.getBitsPerPixel(format) / 8;
        if (data == null || data.length != dataLength) {
            data = new byte[dataLength];
        }
        int rowLength = planes[0].getRowStride();
        if (rowData == null || rowData.length != rowLength) {
            rowData = new byte[rowLength];
        }
        int channelOffset = 0;
        int outputStride = 1;
        for (int i = 0; i < planes.length; i++) {
            switch (i) {
                case 0://Y
                    channelOffset = 0;
                    outputStride = 1;
                    break;
                case 1://U
                    channelOffset = width * height + 1;
                    outputStride = 2;
                    break;
                case 2://V
                    channelOffset = width * height;
                    outputStride = 2;
                    break;
            }

            ByteBuffer buffer = planes[i].getBuffer();
            int rowStride = planes[i].getRowStride();
            int pixelStride = planes[i].getPixelStride();

            int shift = (i == 0) ? 0 : 1;
            int w = width >> shift;
            int h = height >> shift;
            buffer.position(rowStride * (crop.top >> shift) + pixelStride * (crop.left >> shift));
            for (int row = 0; row < h; row++) {
                int length;
                if (pixelStride == 1 && outputStride == 1) {
                    length = w;
                    buffer.get(data, channelOffset, length);
                    channelOffset += length;
                } else {
                    length = (w - 1) * pixelStride + 1;
                    buffer.get(rowData, 0, length);
                    for (int col = 0; col < w; col++) {
                        data[channelOffset] = rowData[col * pixelStride];
                        channelOffset += outputStride;
                    }
                }
                if (row < h - 1) {
                    buffer.position(buffer.position() + rowStride - length);
                }
            }
        }
        if (isMirror) {
            return nv21Mirror(data, width, height);
        }
        return data;
    }

    /**
     * Camera2 YUV420 转 NV21数据
     *
     * @param image    Image
     * @param isMirror 是否镜像,前置需要，后置不需要
     * @return NV21数组
     * @deprecated 存在角标越界问题
     */
    public static byte[] yuv420toNv21(Image image, boolean isMirror) {
        int w = image.getWidth(), h = image.getHeight();
        int i420Size = w * h * 3 / 2;
        Image.Plane[] planes = image.getPlanes();
        int remaining0 = planes[0].getBuffer().remaining();
        int remaining1 = planes[1].getBuffer().remaining();
        int remaining2 = planes[2].getBuffer().remaining();
        int pixelStride = planes[2].getPixelStride();
        int rowOffest = planes[2].getRowStride();
        if (nv21 == null || nv21.length != i420Size) {
            nv21 = new byte[i420Size];
        }
        if (yRawSrcBytes == null || yRawSrcBytes.length != remaining0) {
            yRawSrcBytes = new byte[remaining0];
            uRawSrcBytes = new byte[remaining1];
            vRawSrcBytes = new byte[remaining2];
        }
        planes[0].getBuffer().get(yRawSrcBytes);
        planes[1].getBuffer().get(uRawSrcBytes);
        planes[2].getBuffer().get(vRawSrcBytes);
        if (pixelStride == w) {
            System.arraycopy(yRawSrcBytes, 0, nv21, 0, rowOffest * h);
            System.arraycopy(vRawSrcBytes, 0, nv21, rowOffest * h, rowOffest * h / 2 - 1);
        } else {
            byte[] ySrcBytes = new byte[w * h];
            byte[] vSrcBytes = new byte[w * h / 2 - 1];
            for (int row = 0; row < h; row++) {
                System.arraycopy(yRawSrcBytes, rowOffest * row, ySrcBytes, w * row, w);
                if (row % 2 == 0) {
                    if (row == h - 2) {
                        System.arraycopy(vRawSrcBytes, rowOffest * row / 2, vSrcBytes, w * row / 2, w - 1);
                    } else {
                        System.arraycopy(vRawSrcBytes, rowOffest * row / 2, vSrcBytes, w * row / 2, w);
                    }
                }
            }
            System.arraycopy(ySrcBytes, 0, nv21, 0, w * h);
            System.arraycopy(vSrcBytes, 0, nv21, w * h, w * h / 2 - 1);
        }
        if (isMirror) {
            return nv21Mirror(nv21, w, h);
        }
        return nv21;
    }

    /**
     * NV21镜像
     *
     * @param nv21Data 图像数据
     * @param width    宽
     * @param height   高
     * @return 镜像之后的新数据
     */
    public static byte[] nv21Mirror(byte[] nv21Data, int width, int height) {
        int i;
        int left, right;
        byte temp;
        int startPos = 0;
        for (i = 0; i < height; i++) {
            left = startPos;
            right = startPos + width - 1;
            while (left < right) {
                temp = nv21Data[left];
                nv21Data[left] = nv21Data[right];
                nv21Data[right] = temp;
                left++;
                right--;
            }
            startPos += width;
        }
        int offset = width * height;
        startPos = 0;
        for (i = 0; i < height / 2; i++) {
            left = offset + startPos;
            right = offset + startPos + width - 2;
            while (left < right) {
                temp = nv21Data[left];
                nv21Data[left] = nv21Data[right];
                nv21Data[right] = temp;
                left++;
                right--;
                temp = nv21Data[left];
                nv21Data[left] = nv21Data[right];
                nv21Data[right] = temp;
                left++;
                right--;
            }
            startPos += width;
        }
        return nv21Data;
    }

}
