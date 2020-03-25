package com.example.lab1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.io.FileOutputStream;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab1.R;
import com.example.lab1.ui.MessagePopUp;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements AddNewProduct_PopUp.IAddNewProductListener {

    private HomeViewModel homeViewModel;
    private ListView listView;
    private Button openPopUpButton;
    ProductListAdapter adapter = new ProductListAdapter(getActivity(), R.layout.home_list_layout, homeViewModel.getHomeModelList());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this, new HomeViewModelFactory(getContext())).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = root.findViewById((R.id.kurtosListView));
        openPopUpButton = root.findViewById(R.id.openPopUp);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), KurtosDetail.class);
                Product selectedItem = homeViewModel.getHomeModelList().get(position);
                intent.putExtra("name", selectedItem.getName());
                intent.putExtra("price", selectedItem.getPrice());
                intent.putExtra("description", selectedItem.getDescription());
                startActivity(intent);
            }
        });

        openPopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });
        return root;
    }

    public void OpenDialog() {
        MessagePopUp popUp = new MessagePopUp();
        popUp.show(getActivity().getSupportFragmentManager(), "Add new product");
    }

    @Override
    public void applyTexts(String name, String price, String description) {
        Product product = new Product(price, name, description);
        adapter.add(product);

        FileOutputStream fos = null;
        try {
            fos = getContext().openFileOutput(homeViewModel.FILE_NAME, MODE_PRIVATE);
            fos.write(product.getName().getBytes());
            fos.write(product.getPrice().getBytes());
            fos.write(product.getDescription().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try{
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}