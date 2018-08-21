package br.mobfeel.nutripet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.mobfeel.nutripet.adapter.DogAdapter;
import br.mobfeel.nutripet.dao.DogDao;
import br.mobfeel.nutripet.dao.db.DogDaoBd;
import br.mobfeel.nutripet.model.Dog;

public class MainActivity extends AppCompatActivity implements DogAdapter.DogOnClickListener{

    private List<Dog> dogList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DogAdapter dogAdapter;
    private FloatingActionButton btnAddDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Meus Pets");

        this.btnAddDog = (FloatingActionButton) findViewById(R.id.ma_bt_add);
        this.btnAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), CreateDogActivity.class);
                startActivity(it);
            }
        });

        this.recyclerView = (RecyclerView) findViewById(R.id.dogsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this ));

       // Dog dog = new Dog("Mota", "Pitbull", R.mipmap.ic_launcher);

       // this.dogList.add(dog);
        this.dogAdapter = new DogAdapter(this.dogList, getBaseContext(), this);
        this.recyclerView.setAdapter(this.dogAdapter);
      //  this.recyclerView.addItemDecoration( new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // dogAdapter.notifyDataSetChanged();
        DogDao dogDao = new DogDaoBd(this);
        dogList = dogDao.listAll();
        dogAdapter.setDogList(dogList);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Dog dog;
        switch (resultCode) {
            case 100: //new dog
                String name = data.getStringExtra("name");
                String race = data.getStringExtra("race");
              //  dog = new Dog(name, race, R.mipmap.ic_launcher);
                //dogList.add(dog);
                dogAdapter.setDogList(dogList);
                dogAdapter.notifyDataSetChanged();

                break;
            case 110: //remove dog
                dog = (Dog) data.getSerializableExtra("dog");
                dogList.remove(dog);
                Log.d("DogRemove", Integer.toString(dogList.size()));
                dogAdapter.setDogList(dogList);

                Toast.makeText(this,"Dog was removed!", Toast.LENGTH_LONG).show();
                break;
        }

    }


    @Override
    public void onClickDog(View view, int index) {
        Intent it = new Intent(this, DogDetailActivity.class);
        it.putExtra("dog", dogList.get(index));
        startActivity(it);

    }
}
