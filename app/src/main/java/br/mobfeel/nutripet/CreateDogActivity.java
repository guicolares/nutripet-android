package br.mobfeel.nutripet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.mobfeel.nutripet.dao.DogDao;
import br.mobfeel.nutripet.dao.db.DogDaoBd;
import br.mobfeel.nutripet.model.Dog;

public class CreateDogActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private EditText etName, etRace;
    private Button btnAdd;

    private Dog dog = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dog);
        btnAdd = findViewById(R.id.and_bt_add);
        etName = (EditText) findViewById(R.id.and_et_name);
        etRace = (EditText) findViewById(R.id.ann_et_wheight);


        dog = (Dog) getIntent().getSerializableExtra("dog");
        if(dog != null) { //edit
            etName.setText(dog.getName());
            etRace.setText(dog.getRace());
            btnAdd.setText("Edit");
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate data
                if (dog == null) {
                    createDog();
                }else{
                    editDog();
                }
            }
        });
    }

    private void createDog(){
        Dog dog = new Dog(etName.getText().toString(), etRace.getText().toString());
        DogDao dogDao = new DogDaoBd(CreateDogActivity.this);
        dogDao.create(dog);


        Toast.makeText(this,"Dog was added!", Toast.LENGTH_LONG).show();
        finish();
    }

    private void editDog(){
        dog.setName(etName.getText().toString());
        dog.setRace(etRace.getText().toString());
        DogDao dogDao = new DogDaoBd(this);
        dogDao.update(dog);
        Intent it = new Intent();
        it.putExtra("dog", dog);
        setResult(110, it);

        Toast.makeText(this,"Dog was editted!", Toast.LENGTH_LONG).show();
        finish();
    }


}
