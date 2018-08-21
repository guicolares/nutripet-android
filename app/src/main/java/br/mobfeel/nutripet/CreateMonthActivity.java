package br.mobfeel.nutripet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.mobfeel.nutripet.dao.db.MonthDaoBd;
import br.mobfeel.nutripet.model.Dog;
import br.mobfeel.nutripet.model.Month;

public class CreateMonthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnCreateMonth;
    private EditText etWheight;
    private Spinner monthSpinner;

    private Dog dog;
    private Month month;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_month);
        setTitle("New Weight");
        dog = (Dog) getIntent().getSerializableExtra("dog");
        etWheight = (EditText) findViewById(R.id.ann_et_wheight);
        btnCreateMonth = (Button) findViewById(R.id.anm_bt_add);

        monthSpinner = (Spinner) findViewById(R.id.anm_sp_month);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        month = (Month) getIntent().getSerializableExtra("month");
        if(month != null) { //edit
            btnCreateMonth.setText("Edit");
            etWheight.setText(Double.toString(month.getValue()));
            monthSpinner.setSelection(month.getMonth());
        }

        btnCreateMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validation @TODO
                if(month == null) {
                    createMonth();
                }else{
                    editMonth();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(month != null){
            getMenuInflater().inflate(R.menu.menu_month, menu);
        }

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void createMonth(){
        int monthSelected = monthSpinner.getSelectedItemPosition();
        MonthDaoBd monthDaoBd = new MonthDaoBd(this);
        Month month = new Month(
                monthSelected, dog, Double.parseDouble(etWheight.getText().toString())
        );

        monthDaoBd.create(month);
        Toast.makeText(this,"Wheight was added!", Toast.LENGTH_LONG).show();
        setResult(120);
        finish();
    }

    private void editMonth(){
        int monthSelected = monthSpinner.getSelectedItemPosition();
        MonthDaoBd monthDaoBd = new MonthDaoBd(this);
        month.setMonth(monthSelected);
        month.setValue(Double.parseDouble(etWheight.getText().toString()));
        monthDaoBd.update(month);

        Toast.makeText(this,"Wheight was added!", Toast.LENGTH_LONG).show();
        setResult(120);
        finish();

    }

}
