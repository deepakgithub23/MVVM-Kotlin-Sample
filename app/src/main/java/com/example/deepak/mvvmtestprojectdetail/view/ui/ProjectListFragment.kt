package com.example.deepak.mvvmtestprojectdetail.view.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deepak.mvvmtestprojectdetail.R
import com.example.deepak.mvvmtestprojectdetail.databinding.FragmentProjectListBinding
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.view.adapter.ProjectAdapter
import com.example.deepak.mvvmtestprojectdetail.view.callback.ProjectClickCallback
import com.example.deepak.mvvmtestprojectdetail.viewmodal.ProjectListViewModel

class ProjectListFragment : Fragment() {
    var toolbar:Toolbar?= null
    private var projectAdapter: ProjectAdapter?=null
    private var binding:FragmentProjectListBinding?=null

   companion object {
       var TAG: String="ProjectListFragment"
   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_project_list,container, false)

        projectAdapter= ProjectAdapter(projectClickCallback)
        binding!!.projectList.setAdapter(projectAdapter)
        binding!!.setIsLoading(true)

        return binding!!.getRoot()
    }

    private val projectClickCallback = object :ProjectClickCallback {
        override
            fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       val viewModal:ProjectListViewModel= ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        observeViewModel(viewModal)
    }

    private fun observeViewModel(viewModal: ProjectListViewModel) {
        viewModal.getPorjectListObservable().observe(this, Observer<List<Project>> { project ->
            if (project != null) {
                binding!!.setIsLoading(false)
                projectAdapter!!.setProjectList(project)
            }
        })

    }
}