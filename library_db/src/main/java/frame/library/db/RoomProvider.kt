package frame.library.db

import android.content.Context
import androidx.room.Room

object RoomProvider {

    private var objRoom: AppDatabase? = null

    fun init(context: Context, dbName: String = "database_simple.db") {
        objRoom = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            dbName
        ).allowMainThreadQueries().build()
    }

    private val obj: AppDatabase
        get() = requireNotNull(objRoom) { "Application : RoomProvider.init(context)" }

    suspend fun insertTestDao(testDb: TestDb): Long {
        return obj.testDao.insert(testDb)
    }

    suspend fun getAllTestAd(): List<TestDb> {
        return obj.testDao.getAll()
    }

}