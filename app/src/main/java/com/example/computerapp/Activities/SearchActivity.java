package com.example.computerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computerapp.Adapters.NotebookAdaptor;
import com.example.computerapp.Clients.ApiClient;
import com.example.computerapp.Models.Desktop;
import com.example.computerapp.Models.Notebook;
import com.example.computerapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    EditText searchField;
    RecyclerView resultList;
    NotebookAdaptor notebookAdaptor = new NotebookAdaptor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = findViewById(R.id.search_field);
        resultList = findViewById(R.id.result_list);
        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        
        searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notebookSearch();
            }
        });

    }

    private void notebookSearch() {
        Call<List<Notebook>> notebookCall = ApiClient.getNotebookService().getFilteredNotebooks("Bearer "+ getToken(),
                searchField.getText().toString());
        notebookCall.enqueue(new Callback<List<Notebook>>() {
            @Override
            public void onResponse(Call<List<Notebook>> call, Response<List<Notebook>> response) {
                if (response.isSuccessful()){

                    String message = "Response Successful";
                    Toast.makeText(SearchActivity.this, message, Toast.LENGTH_LONG).show();

                   if (response.body() != null);{
                        List<Notebook> notebooks = new ArrayList<>();
                        notebooks.addAll(response.body());
                        notebookAdaptor.setData(notebooks);
                        resultList.setAdapter(notebookAdaptor);
                   }

                }else{
                    String message = "Response Is Not Successful";
                    Toast.makeText(SearchActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notebook>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(SearchActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });

    }

    private String getToken(){
        SharedPreferences prefs=getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        return prefs.getString("token","");
    }
}