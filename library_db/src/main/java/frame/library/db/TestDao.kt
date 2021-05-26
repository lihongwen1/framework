package frame.library.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction

@Dao
interface TestDao {
    @Transaction
    @Insert(entity = TestDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(test: TestDb): Long
}