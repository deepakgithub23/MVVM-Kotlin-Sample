package com.example.deepak.mvvmtestprojectdetail.viewmodal

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.service.repository.ProjectRepository

class ProjectViewModal : AndroidViewModel {

  private  var projectObservable: LiveData<Project>? = null
  private final var projectId:String?=null
    public var project:ObservableField<Project> = ObservableField<Project>()

  constructor(application: Application, projectId:String) : super(application) {
    this.projectId= projectId
    projectObservable = ProjectRepository.getInstance().getProjectDetails("Google", projectId)
  }

  public fun getObservableProject(): LiveData<Project>? {

    return projectObservable
  }

  fun setProject(project: Project){
    return this.project.set(project)
  }


  public class Factory : ViewModelProvider.NewInstanceFactory{

      private final var application:Application
      private final var projectId:String

    constructor(application: Application, projectId: String){
      this.application= application
      this.projectId= projectId
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return ProjectViewModal(application , projectId) as T
    }
  }

}

