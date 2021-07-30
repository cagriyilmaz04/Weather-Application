package com.example.weatherapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.viewmodel.mainviewmodel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    var str1="Istanbul"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewmodels: mainviewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodels= ViewModelProviders.of(this).get(mainviewmodel::class.java)
        binding.toolbar.title="Weather App"
        setSupportActionBar(binding.toolbar)

        followData()

    }
    private fun followData(){
        var strcolor="#FFFFFFFF"
        var black="#FF000000"
        downloadData()
        var sayi=0
        var lengt=0
        viewmodels.weather_data.observe(this@MainActivity, Observer {weather->
            weather?.let {
                sayi=convert_to_Int(it.weather.get(0).icon.toString())
                lengt=it.weather.get(0).icon.length
                --lengt;
                if(it.weather.get(0).icon[lengt]=='n'){
                    binding.constraintLayout.setBackgroundResource(R.drawable.night)
                    binding.textViewAciklama.setTextColor(Color.parseColor(strcolor))
                    binding.textViewConstant.setTextColor(Color.parseColor(strcolor))
                    binding.textHavaDurumu.setTextColor(Color.parseColor(strcolor))
                    binding.textSehir.setTextColor(Color.parseColor(strcolor))
                }else{
                    binding.constraintLayout.setBackgroundResource(R.drawable.daytime)
                    binding.textViewAciklama.setTextColor(Color.parseColor(black))
                    binding.textViewConstant.setTextColor(Color.parseColor(black))
                    binding.textHavaDurumu.setTextColor(Color.parseColor(black))
                    binding.textSehir.setTextColor(Color.parseColor(black))
                }
                when(sayi){

                    1->{
                        Picasso.get().load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_01.png").into(binding.imageView)
                    }
                    2->{
                        Picasso.get().load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_02.png").into(binding.imageView)
                    }
                    3->{
                        Picasso.get().load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_03.png").into(binding.imageView)
                    }
                    4->{
                        Picasso.get().load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_04.png").into(binding.imageView)
                    }
                    9->{
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_09.png")
                                .into(binding.imageView)
                    }
                    10->{
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_10.png")
                                .into(binding.imageView)
                    }
                    11->{
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_11.png")
                                .into(binding.imageView)
                    }
                    13->{
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_13.png")
                                .into(binding.imageView)
                    }
                    50->{
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/emrealtunbilek/havadurumukotlin/master/app/src/main/res/drawable/icon_50.png")
                                .into(binding.imageView)

                    }
                }
                binding.progressBar.visibility= View.GONE
                binding.textSehir.text="${it.cityName}"
                var str1=it.weather.get(0).description.toUpperCase()
                binding.textViewAciklama.text="${str1}"
                binding.textHavaDurumu.text="Hava Durumu: ${it.main.temp.toInt()}"
            }
        })
        viewmodels.is_it_loaded.observe(this, Observer { our_value->

            if(our_value==true){
                binding.progressBar.visibility= View.GONE
                binding.textViewConstant.textSize= 30.0F
                binding.textViewConstant.text="°C"
            }else{
                /* binding.progressBar.isInvisible=false
                binding.textHavaDurumu.isInvisible=true
                binding.textSehir.isInvisible=true
                binding.textViewAciklama.isInvisible=true
                binding.textViewConstant.isInvisible=true
                 */


            }
        })
    }
    private fun convert_to_Int(icon: String): Int {
        var i=0;
        while(i<icon.length){
            if(icon.get(i).equals('n')){
                break
            }
            ++i
        }
        val sayi=icon.substring(0,i-1)
        val temp=sayi.toInt()
        return temp
    }
    private fun downloadData(){
        viewmodels.getData(str1)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val item= menu!!.findItem(R.id.search_view)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener(this@MainActivity)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_view->{
            }
        }
        return true
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        str1= query.toString()
        followData()
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        str1= newText.toString()
        // followData()
        return true
    }

}