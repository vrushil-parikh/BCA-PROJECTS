package com.example.kotlinapp

import java.util.Scanner

class MyClass{

    companion object{
        @JvmStatic
        fun main(array: Array<String>){
            val arr= listOf<Int>(1,2,3,5,6,7)
            for (i in arr)
            {
                while (i<5) {
                    print("$i, ")
                }
            }
        }
    }
}