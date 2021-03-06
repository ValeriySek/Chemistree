package com.selflearning.chemistree.chemistry.elements;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.chemistry.inorganic.acids.AcidsDao;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.chemistry.inorganic.bases.BasesDao;
import com.selflearning.chemistree.chemistry.inorganic.oxides.Oxides;
import com.selflearning.chemistree.chemistry.inorganic.oxides.OxidesDao;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.worker.DatabaseWorker;



@Database(entities = {Acids.class, Bases.class, Oxides.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(DatabaseWorker.class).build();
                        WorkManager.getInstance(context.getApplicationContext()).enqueue(request);

                    }
                })
                .build();
    }

//    public abstract ElementDao elementDao();
    public abstract BasesDao basesDao();
    public abstract AcidsDao acidsDao();
    public abstract OxidesDao oxidesDao();
}

