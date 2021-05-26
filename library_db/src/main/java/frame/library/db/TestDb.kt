package frame.library.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_db")
class TestDb(
    @ColumnInfo(name = "key")
    @PrimaryKey(autoGenerate = true)
    val key: Long,
    @ColumnInfo(name = "value")
    val value: String
)