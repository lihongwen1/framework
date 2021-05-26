package frame.library.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TestDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val testDao: TestDao
}