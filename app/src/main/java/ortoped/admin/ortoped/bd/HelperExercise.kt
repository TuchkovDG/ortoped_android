package ortoped.admin.ortoped.bd

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ortoped.admin.ortoped.App
import ortoped.admin.ortoped.model.ExerciseModel

class HelperExercise : SQLiteOpenHelper(App.instance.applicationContext, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(("CREATE TABLE " + TABLE_EXERCISES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_STATUS + " BLOB" + ")"))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EXERCISES")
        onCreate(db)
    }

    fun addNewExerciseModels(exercise: ArrayList<ExerciseModel>) {
        val db = this.writableDatabase

        for (item: ExerciseModel in exercise) {
            val cursor = db.query(TABLE_EXERCISES, null, "$COLUMN_TITLE = ?",
                    arrayOf(item.title), null, null, null)
            val cv = ContentValues()
            cv.put(COLUMN_TITLE, item.title)
            cv.put(COLUMN_DESCRIPTION, item.description)
            cv.put(COLUMN_STATUS, item.status)
            if (cursor.moveToFirst()) {
                db.update(TABLE_EXERCISES, cv, "$COLUMN_TITLE = ?", arrayOf(item.title))
            } else {
                db.insert(TABLE_EXERCISES, null, cv)
            }
            cursor.close()
        }

        db.close()
    }

    fun getAllExercises(): ArrayList<ExerciseModel> {
        val db = this.writableDatabase
        val cursor = db.query(TABLE_EXERCISES, null, null,
                null, null, null, null)

        val events: ArrayList<ExerciseModel> = ArrayList()

        if (cursor.moveToFirst()) {

            do {
                events.add(ExerciseModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS)) == 0))
            } while (cursor.moveToNext())
            cursor.close()
            db.close()
        }

        return events
    }

    fun deleteAllExercises() {
        val db = this.writableDatabase
        db.delete(TABLE_EXERCISES, null, null)
        db.close()
    }

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "exerciseDB.db"
        private const val TABLE_EXERCISES = "exercises"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_STATUS = "status"
    }
}