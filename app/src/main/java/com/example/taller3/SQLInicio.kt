package com.example.taller3

import android.provider.BaseColumns

    object SQLInicio {
        object UserEntry : BaseColumns {
            const val TABLE_NAME = "usuario"
            const val COLUMN_NAME_NOMBRE = "nombre"
        }
    }
