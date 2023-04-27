package com.example.apiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.apiretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var myAdapter: MyAdapter


    //private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getProductData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api call is success
                var responseBody=response.body()    ////!!->condition if(prod data !=null)
                val productList= responseBody?.products!! //?->age ki method call nhi hogi if response body is empty
                  myAdapter=MyAdapter(this@MainActivity, productList)
                binding.rv.adapter=myAdapter
                binding.rv.layoutManager=LinearLayoutManager(this@MainActivity)





            //                val collectDataStringBuilder= StringBuilder()
//                for(myData in productList)
//                {
//                    collectDataStringBuilder.append(myData.title+" ")
//                }
//              binding.textview.text=collectDataStringBuilder

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if api call is fails
                Log.d("mainActivity","onFailure"+t.message)
            }
        })
    }
}