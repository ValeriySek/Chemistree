package com.selflearning.chemistree.ui.notifications;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.data.User;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsFragment extends Fragment {


//    private RecyclerView recyclerView;
//    private AcidsAdapter acidsAdapter;
//    private ElementsAdapter elementsAdapter;
//    private ElementViewModel viewModel;
//    ElementRepository repository;

    private TextView tvName;
    private TextView tvEmail;
    private CircleImageView imageView;
    private User user;
    private static final int RESULT_LOAD = 101;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tvName = root.findViewById(R.id.tvUserName);
        tvEmail = root.findViewById(R.id.tvUserEmail);
        imageView = root.findViewById(R.id.civUser);

        user = User.getInstance();
        String name = user.getFirstName();
        String email = user.getUid();

        tvName.setText(name);
        tvEmail.setText(email);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD);
            }
        });

        Log.i("INFORMA", "in fragment");






//        viewModel = new ViewModelProvider(this).get(ElementViewModel.class);
////        acidsAdapter = new AcidsAdapter();
//        elementsAdapter = new ElementsAdapter();
//
//        viewModel.getAllElements().observe(getViewLifecycleOwner(), new Observer<List<Element>>() {
//            @Override
//            public void onChanged(List<Element> elements) {
//                elementsAdapter.setElementList(elements);
//            }
//        });

//        viewModel.getBases().observe(getViewLifecycleOwner(), new Observer<List<Bases>>() {
//            @Override
//            public void onChanged(List<Bases> bases) {
//
//                acidsAdapter.setDataList(bases);
//            }
//        });
//        repository = new ElementRepository((Application) getContext().getApplicationContext());
//        List<Bases> bases = repository.getBasesList();
//        DatabaseAccess.getInstance(getContext());
//        final List<Bases> basesList = DatabaseAccess.getAllBases();

//        recyclerView = root.findViewById(R.id.recycler_view_elements);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////        recyclerView.setAdapter(acidsAdapter);
//        recyclerView.setAdapter(elementsAdapter);



        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            try{
                Uri imageUrl = null;
                if (data != null) {
                    imageUrl = data.getData();
                }
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUrl);
                Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}