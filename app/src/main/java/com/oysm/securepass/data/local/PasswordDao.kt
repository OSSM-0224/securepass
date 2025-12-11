package com.oysm.securepass.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.oysm.securepass.data.models.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Insert
    fun insertPassword(password: PasswordEntity): Long

    @Update
    fun updatePassword(password: PasswordEntity)

    @Delete
    fun deletePassword(password: PasswordEntity)

    @Query("SELECT * FROM passwords ORDER BY createdAt DESC")
    fun getAllPasswords(): Flow<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    fun getPasswordById(id: Int): PasswordEntity?

    @Query("DELETE FROM passwords WHERE id = :id")
    fun deletePasswordById(id: Int)

    @Query("SELECT COUNT(*) FROM passwords")
    fun getPasswordCount(): Int
}