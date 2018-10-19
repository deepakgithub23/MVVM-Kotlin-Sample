package com.example.deepak.mvvmtestprojectdetail.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deepak.mvvmtestprojectdetail.BR.project
import com.example.deepak.mvvmtestprojectdetail.R
import com.example.deepak.mvvmtestprojectdetail.databinding.FragmentProjectDetailsBinding
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.viewmodal.ProjectViewModal

class ProjectFragment : Fragment() {
    var toolbar: Toolbar? = null
    var mycallBack: MyInterface? = null

    private var binding: FragmentProjectDetailsBinding? = null

    companion object {
        var KEY_PROJECT_ID: String = "project_id"
        fun forProject(projectId: String): ProjectFragment {
            var projectFragment: ProjectFragment = ProjectFragment()
            var args: Bundle = Bundle()
            args.putString(KEY_PROJECT_ID, projectId)

            projectFragment.setArguments(args)

            return projectFragment
        }
    }

    interface MyInterface {
        fun onTrigger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)
        return binding!!.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var factory: ProjectViewModal.Factory = ProjectViewModal.Factory(activity?.application!!, arguments!!.getString(KEY_PROJECT_ID))
        var viewModal: ProjectViewModal = ViewModelProviders.of(this, factory).get(ProjectViewModal::class.java!!)
        binding!!.setProjectViewModel(viewModal)
        binding!!.setIsLoading(true)
        observeViewModel(viewModal)

    }

    private fun observeViewModel(viewModel: ProjectViewModal) {
        // Observe project data
        viewModel.getObservableProject()!!.observe(this, object : Observer<Project> {
            override fun onChanged(project: Project?) {
                if (project != null) {
                    binding!!.setIsLoading(false)
                    viewModel.setProject(project)
                }
            }
        })
    }

    /** Creates project fragment for specific project ID  */
    fun forProject(projectID: String): ProjectFragment {
        val fragment = ProjectFragment()
        val args = Bundle()

        args.putString(KEY_PROJECT_ID, projectID)
        fragment.arguments = args

        return fragment
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MyInterface) {
            mycallBack = activity as MyInterface
        }
    }


}
