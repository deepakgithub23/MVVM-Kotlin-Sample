package com.example.deepak.mvvmtestprojectdetail.viewmodal

import android.app.Application
import android.arch.lifecycle.*
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.service.repository.ProjectRepository

class ProjectListViewModel: AndroidViewModel {

    private var projectlistObservable:LiveData<List<Project>>? = null

   public constructor(application:Application) : super(application) {
    projectlistObservable= ProjectRepository.getInstance().getProjectList("Google")
    }

    fun getPorjectListObservable(): LiveData<List<Project>>{
         return  projectlistObservable!!
     }
}