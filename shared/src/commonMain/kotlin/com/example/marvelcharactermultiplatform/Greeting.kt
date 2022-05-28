package com.example.marvelcharactermultiplatform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}