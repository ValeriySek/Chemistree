package com.selflearning.chemistree.f_home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.activities.RegisterActivity;
import com.selflearning.chemistree.adapter.ReactionAdapter;
import com.selflearning.chemistree.data.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReactionAdapter adapter = null;
    private FirebaseAuth mAuth;

    private ImageView imageViewInner;
    private ImageView imageViewOuter;
    private Button signOut;

    private HomeViewModel homeViewModel;
    Animation animation1;
    Animation animation2;

    AdView adView;


    User user = User.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mAuth = FirebaseAuth.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = user.getUid();
        DocumentReference reference = db.collection("users").document(userId);

        Map<String, Object> userr = new HashMap<>();
        userr.put("name", user.getFirstName());
        reference.set(userr).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "onSuccess", Toast.LENGTH_SHORT).show();
                Log.i("HomeFr", "onSuccess put user");
        }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
                Log.i("HomeFr", "onFailure put user " + e);
            }
        });

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
        signOut = root.findViewById(R.id.signOut);

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

        Toast.makeText(getActivity(), "" + Locale.getDefault().getLanguage(), Toast.LENGTH_SHORT).show();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                getActivity().finish();
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
            }
        });



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
        return root;
    }


    @Override
    public void onPause() {
//        animation1.cancel();
//        animation2.cancel();
        super.onPause();

    }
}