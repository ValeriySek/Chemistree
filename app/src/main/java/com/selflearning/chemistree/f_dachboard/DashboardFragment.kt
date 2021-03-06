package com.selflearning.chemistree.f_dachboard

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.R
import com.selflearning.chemistree.databinding.FragmentDashboardBinding
import selflearning.libraries.actions.Actions

class DashboardFragment : Fragment() {

    private val dashboardViewModel by lazy {
        ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setUpToolbar(binding.root)
        binding.imageButtonMendeleev.setOnClickListener{
//            val intent = Intent("com.selflearning.chemistree.open"
//                    requireContext(), Class.forName("com.selflearning.chemistree.f_mendeleev_table.MyMyActivity")
//            ).setPackage(requireContext().packageName)
//            intent.component = ComponentName("com.selflearning.chemistree.f_mendeleev_table", "com.selflearning.chemistree.f_mendeleev_table.MyMyActivity")
            startActivity(Actions.openFirstIntent(requireContext()))
        }


        //        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
//
//        CategoryAdapter adapter = new CategoryAdapter(DatabaseAccess.getInstance(context).getAllTables(), context);
//        recyclerView.setAdapter(adapter);
//
//        int i = 10;
//        recyclerView.addItemDecoration(new TablesGridItemDecoration(i));
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return binding.root
    }

    private fun setUpToolbar(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.app_bar)
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
    }
}