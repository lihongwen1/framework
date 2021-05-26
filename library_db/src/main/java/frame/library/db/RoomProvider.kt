package frame.library.db

import android.content.Context
import androidx.room.Room

class RoomProvider {

    suspend fun insertTestDao(testDb: TestDb): Long {
        return obj.testDao.insert(testDb)
    }

    companion object {

        private var objRoom: AppDatabase? = null

        fun init(context: Context, dbName: String = "database_simple.db") {
            objRoom = Room
                .databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    dbName
                )
                .allowMainThreadQueries()
                .build()
        }

        val obj: AppDatabase
            get() = requireNotNull(objRoom) { "Application : RoomProvider.init(context)" }

    }

}