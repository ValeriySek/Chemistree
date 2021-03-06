package com.selflearning.chemistree.f_home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.selflearning.chemistree.f_registration.RegisterActivity
import com.selflearning.chemistree.adapter.ReactionAdapter
import com.selflearning.chemistree.data.User.Companion.instance
import com.selflearning.chemistree.databinding.FragmentHomeBinding
import com.selflearning.chemistree.utilities.extentions.dpi
import java.util.*

class HomeFragment : Fragment() {
    private val recyclerView: RecyclerView? = null
    private val adapter: ReactionAdapter? = null
    private var mAuth: FirebaseAuth? = null
    private val imageViewInner: ImageView? = null
    private val imageViewOuter: ImageView? = null
    private var signOut: Button? = null
    private var homeViewModel: HomeViewModel? = null
    var animation1: Animation? = null
    var animation2: Animation? = null
    var adView: AdView? = null
    var user = instance

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var dpi = dpi()

//        binding.imageView5.viewWidth(200)
//        binding.imageView5.requestLayout()

//        Log.i("TAGG", "${binding.imageView4.width}")



        mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val userId = user?.uid
        val reference = db.collection("users").document(userId ?: "")
        val userr: MutableMap<String, Any?> = HashMap()
        userr["name"] = user?.firstName
        reference.set(userr).addOnSuccessListener {
            Toast.makeText(activity, "onSuccess", Toast.LENGTH_SHORT).show()
            Log.i("HomeFr", "onSuccess put user")
        }.addOnFailureListener { e ->
            Toast.makeText(activity, "onFailure", Toast.LENGTH_SHORT).show()
            Log.i("HomeFr", "onFailure put user $e")
        }

//
//        DocumentReference refe = db
//                .collection("users").document(user.getUid())
//                .collection("games").document("game");
//
//        Map<String, Object> gameScore = new HashMap<>();
//        gameScore.put("day1", 150);
//
//        refe.set(gameScore).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(getActivity(), "onSuccess", Toast.LENGTH_SHORT).show();
//                Log.i("HomeFr", "onSuccess put game");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
//                Log.i("HomeFr", "onFailure put user " + e);
//            }
//        });


//        CollectionReference refe = db
//                .collection("users").document(user.getUid())
//                .collection("games");
//
//        Map<String, Object> gameScore = new HashMap<>();
//        gameScore.put("game", 150);
//
//        refe.add(gameScore).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//
//                Log.i("HomeFr", "onSuccess put user");
//                Toast.makeText(getActivity(), "onSuccess", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
//                Log.i("HomeFr", "onFailure put user " + e);
//            }
//        });


//        imageViewInner = root.findViewById(R.id.ivInner);
//        imageViewOuter = root.findViewById(R.id.ivOuter);
        signOut = binding.signOut

//        MobileAds.initialize(getActivity(), "ca-app-pub-6484527932538223~1621561795");

//        animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.rot_clockwize);
////
////        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder()
////                .setTestDeviceIds(Arrays.asList("8663B564D80AF252EA9ADB2B6EAA2059"))
////                .build());
////
////        adView = root.findViewById(R.id.adView1);
////        AdRequest adRequest = new AdRequest.Builder()
////                .build();
////
////        adView.loadAd(adRequest);
//        animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.rottion_anti_clockwize);
//        imageViewInner.startAnimation(animation1);
//        imageViewOuter.startAnimation(animation2);
        Toast.makeText(activity, "" + Locale.getDefault().language, Toast.LENGTH_SHORT).show()
        signOut?.setOnClickListener(View.OnClickListener {
            mAuth!!.signOut()
            startActivity(Intent(activity, RegisterActivity::class.java))
            requireActivity().finish()
            //                Locale locale = new Locale("en");
//
//                Resources resources = getResources();
//                Configuration configuration = resources.getConfiguration();
//                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//
//                configuration.setLocale(locale);
//
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
//                    getActivity().getApplicationContext().createConfigurationContext(configuration);
//                } else {
//                    resources.updateConfiguration(configuration,displayMetrics);
//                }

//                Configuration configuration = new Configuration();
//                Locale.setDefault(locale);
//                configuration.setLocale(locale);
//                getActivity().getBaseContext().createConfigurationContext(configuration);
//                Toast.makeText(getActivity(), "" + Locale.getDefault().getLanguage(), Toast.LENGTH_SHORT).show();
        })


//        recyclerView = root.findViewById(R.id.rvReactions);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        adapter = new ReactionAdapter(ReactionEntry.initReactionList(getContext()));
//        recyclerView.setAdapter(adapter);


//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Log.i("TAGG", "${binding.imageView4.measuredWidth}")
    }

    override fun onResume() {
        super.onResume()

        Log.i("TAGG", "${resources.displayMetrics.density}")
//        Log.i("TAGG", "${binding.imageView4.layoutParams.width}")
//        Log.i("TAGG", "${binding.imageView4.layoutParams.height}")
    }

    override fun onPause() {
//        animation1.cancel();
//        animation2.cancel();
        super.onPause()
    }
}