package frame.library.db

import androidx.room.*

@Dao
interface TestDao {
    @Transaction
    @Insert(entity = TestDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(test: TestDb): Long

    @Transaction
    @Query("SELECT * FROM test_db")
    suspend fun getAll(): List<TestDb>
}