package com.shuttleclone.userapp.AppDb

import android.app.Application
import androidx.lifecycle.LiveData
import com.shuttleclone.userapp.AppDb.Dao.RecentSearchDao
import com.shuttleclone.userapp.AppDb.models.RecentSearchData
import com.shuttleclone.userapp.utils.subscribeOnBackground

class AppDbRepository(application: Application) {

    private val database:AppDatabase by lazy { AppDatabase.getInstance(application) }
    private val recentSearchDao: RecentSearchDao by lazy { database.recentSearchDao() }
    private val allRecords: LiveData<List<RecentSearchData>> by lazy { recentSearchDao.getAllRecentSearchData() }

    fun insert(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.insert(recentSearchData)
        }
    }

    fun update(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.update(recentSearchData)
        }
    }

    fun delete(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.delete(recentSearchData)
        }
    }

    fun deleteAllRecentSearchData() {
        subscribeOnBackground {
            recentSearchDao.deleteAllRecentSearchData()
        }
    }

    fun getAllRecentSearchData(): LiveData<List<RecentSearchData>> {
        return allRecords
    }



}