package com.oysm.securepass.data.repository

import com.oysm.securepass.data.local.PasswordDao
import com.oysm.securepass.data.models.PasswordEntity
import com.oysm.securepass.security.EncryptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PasswordRepository(private val passwordDao: PasswordDao) {

    val allPasswords: Flow<List<PasswordEntity>> = passwordDao.getAllPasswords()

    suspend fun insertPassword(
        accountType: String,
        username: String,
        password: String
    ): Long {
        return withContext(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(password)
            val passwordEntity = PasswordEntity(
                accountType = accountType,
                username = username,
                encryptedPassword = encryptedPassword
            )
            passwordDao.insertPassword(passwordEntity)
        }
    }

    suspend fun updatePassword(
        id: Int,
        accountType: String,
        username: String,
        password: String
    ) {
        withContext(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(password)
            val passwordEntity = PasswordEntity(
                id = id,
                accountType = accountType,
                username = username,
                encryptedPassword = encryptedPassword,
                updatedAt = System.currentTimeMillis()
            )
            passwordDao.updatePassword(passwordEntity)
        }
    }

    suspend fun deletePassword(id: Int) {
        withContext(Dispatchers.IO) {
            passwordDao.deletePasswordById(id)
        }
    }

    suspend fun getPasswordById(id: Int): PasswordEntity? {
        return withContext(Dispatchers.IO) {
            passwordDao.getPasswordById(id)
        }
    }

    fun getDecryptedPassword(encryptedPassword: String): String {
        return EncryptionUtil.decrypt(encryptedPassword)
    }
}