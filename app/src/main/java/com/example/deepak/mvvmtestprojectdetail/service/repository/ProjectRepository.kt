package com.example.deepak.mvvmtestprojectdetail.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.service.repository.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository {

    private var githubService: GithubService? = null
    private var projectRepository: ProjectRepository? = null

    constructor() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        githubService = retrofit.create(GithubService::class.java)
    }

    //create instance of class
    companion object Factory {
        var projectRepository: ProjectRepository? = null
        @JvmStatic
        fun getInstance(): ProjectRepository {
            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
            return projectRepository as ProjectRepository
        }
    }

    //get list of the project from Git
    fun getProjectList(userId: String): LiveData<List<Project>> {
        var data: MutableLiveData<List<Project>> = MutableLiveData()
        githubService!!.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                data.setValue(null)
            }

            override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {
                data.setValue(response!!.body())
            }
        })
        return data
    }

    //get project details aganist the project
    fun getProjectDetails(userId: String, projectName: String): LiveData<Project> {
        var datadetail: MutableLiveData<Project> = MutableLiveData()
        githubService!!.getProjectDetails(userId, projectName).enqueue(object : Callback<Project> {
            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                simulateDelay()
                datadetail.setValue(null)
            }
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {
                datadetail.setValue(response!!.body())
            }
        })
        return datadetail
    }

    //stop thread for a while
    private fun simulateDelay() {
        try {
            Thread.sleep(1000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}


