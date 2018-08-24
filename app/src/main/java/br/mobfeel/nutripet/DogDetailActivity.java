package br.mobfeel.nutripet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.mobfeel.nutripet.adapter.DetailAdapter;
import br.mobfeel.nutripet.dao.db.DogDaoBd;
import br.mobfeel.nutripet.dao.db.MonthDaoBd;
import br.mobfeel.nutripet.model.Dog;
import br.mobfeel.nutripet.model.Month;

public class DogDetailActivity extends AppCompatActivity implements DetailAdapter.MonthOnClickListener{

    private Dog dog;

    private List<Month> monthList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DetailAdapter detailAdapter;
    private FloatingActionButton btnNewMonth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_dog_detail);
        setTitle("Details");
        super.onCreate(savedInstanceState);
        this.dog = (Dog) getIntent().getSerializableExtra("dog");
        setTitle(this.dog.getName());

        recyclerView = (RecyclerView) findViewById(R.id.ad_rv_items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detailAdapter = new DetailAdapter(dog, monthList, this, this);
        recyclerView.setAdapter(detailAdapter);

        btnNewMonth = findViewById(R.id.add_fb_newMonth);
        btnNewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), CreateMonthActivity.class );
                it.putExtra("dog", dog);
                startActivityForResult(it, 120);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        MonthDaoBd monthDaoBd = new MonthDaoBd(this);
        monthList = monthDaoBd.findByDogId(dog.getId());
        detailAdapter.setMonthList(monthList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_remove_dog:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete");
                alert.setTitle("Are you sure you want to delete ? ");
                alert.setCancelable(true);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DogDaoBd dogDaoBd = new DogDaoBd(DogDetailActivity.this);
                        dogDaoBd.delete(DogDetailActivity.this.dog);
                        Toast.makeText(DogDetailActivity.this, "Dog was removed",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).show();



                break;
            case R.id.action_edit_dog:
                Intent it = new Intent(this, CreateDogActivity.class);
                it.putExtra("dog", dog);
                startActivityForResult(it, 110);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 110:
                if(data != null ) {
                    dog = (Dog) data.getSerializableExtra("dog");
                    detailAdapter.setDog(dog);
                }
        }
        Log.d("ResultEdit", Integer.toString(requestCode));
    }


    @Override
    public void onClickMonth(View view, int index) {
        Intent it = new Intent(this, CreateMonthActivity.class);
        it.putExtra("month", monthList.get(index));
        it.putExtra("dog", dog);
        startActivityForResult(it, 120);

    }
}
