package com.shuttleclone.userapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shuttleclone.userapp.AppDb.AppDbRepository
import com.shuttleclone.userapp.AppDb.models.RecentSearchData

class RecentSearchViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = AppDbRepository(app)
    private val allRecentSearchData = repository.getAllRecentSearchData()

    fun insert(recentSearchData: RecentSearchData) {
        repository.insert(recentSearchData)
    }

    fun update(recentSearchData: RecentSearchData) {
        repository.update(recentSearchData)
    }

    fun delete(RecentSearchData: RecentSearchData) {
        repository.delete(RecentSearchData)
    }

    fun deleteAllRecentSearchData() {
        repository.deleteAllRecentSearchData()
    }

    fun getAllRecentSearchData(): LiveData<List<RecentSearchData>> {
        return allRecentSearchData
    }

}