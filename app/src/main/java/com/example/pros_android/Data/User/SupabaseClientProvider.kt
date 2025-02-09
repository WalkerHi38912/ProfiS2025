package com.example.pros_android.Data.User

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientProvider {
    val client = createSupabaseClient(
        supabaseUrl = "https://gbfkbbjrttbordcmottr.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdiZmtiYmpydHRib3JkY21vdHRyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzgzNjc3MDcsImV4cCI6MjA1Mzk0MzcwN30.H2siRomg54ug0SdZIuO8Bv4arCXE3hK-RLqAtQK3PJ4"
    ) {
        install(Auth)
        install(Postgrest)
    }
}