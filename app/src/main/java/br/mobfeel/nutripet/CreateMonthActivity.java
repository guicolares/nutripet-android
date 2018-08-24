package br.mobfeel.nutripet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.mobfeel.nutripet.dao.db.DogDaoBd;
import br.mobfeel.nutripet.dao.db.MonthDaoBd;
import br.mobfeel.nutripet.model.Dog;
import br.mobfeel.nutripet.model.Month;
import br.mobfeel.nutripet.util.Validation;

public class CreateMonthActivity extends AppCompatActivity implements View.OnClickListener {

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
        initialize();

        month = (Month) getIntent().getSerializableExtra("month");
        if(month != null) { //edit
            btnCreateMonth.setText("Edit");
            etWheight.setText(Double.toString(month.getValue()));
            monthSpinner.setSelection(month.getMonth());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(month != null){
            getMenuInflater().inflate(R.menu.menu_month, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_remove_month:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete");
                alert.setTitle("Are you sure you want to delete ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MonthDaoBd monthDaoBd = new MonthDaoBd(CreateMonthActivity.this);
                        monthDaoBd.delete(CreateMonthActivity.this.month);
                        Toast.makeText(CreateMonthActivity.this, "Month was removed",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize(){
        dog = (Dog) getIntent().getSerializableExtra("dog");
        etWheight = (EditText) findViewById(R.id.ann_et_wheight);
        btnCreateMonth = (Button) findViewById(R.id.anm_bt_add);
        btnCreateMonth.setOnClickListener(this);

        monthSpinner = (Spinner) findViewById(R.id.anm_sp_month);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (Validation.weight(etWheight.getText().toString())) {
            if (month == null) {
                createMonth();
            } else {
                editMonth();
            }
        }else{
            Toast.makeText(
                    getBaseContext(),
                    "Invalid Data!",
                    Toast.LENGTH_LONG).show();
        }
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

        Toast.makeText(this,"Wheight was editted!", Toast.LENGTH_LONG).show();
        setResult(120);
        finish();

    }

}
