package com.example.pros_android.Data.User.model

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://iwqpujllrlpzzvwjfcjd.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Iml3cXB1amxscmxwenp2d2pmY2pkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzkxNzgzMDksImV4cCI6MjA1NDc1NDMwOX0.J5pm1B5BA0k2Inr6C8ehCFmerREGs-nKU-qVyipDH2E"
    ) {
        install(GoTrue)
        install(Postgrest)
    }
}

/*
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientProvider {
    val client = createSupabaseClient(
        supabaseUrl = "https://iwqpujllrlpzzvwjfcjd.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Iml3cXB1amxscmxwenp2d2pmY2pkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzkxNzgzMDksImV4cCI6MjA1NDc1NDMwOX0.J5pm1B5BA0k2Inr6C8ehCFmerREGs-nKU-qVyipDH2E"
    ) {
        install(Auth)
        install(Postgrest)
    }
}

 */