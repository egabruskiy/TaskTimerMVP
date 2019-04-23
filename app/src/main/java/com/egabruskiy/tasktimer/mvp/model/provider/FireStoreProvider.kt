package com.egabruskiy.tasktimer.mvp.model.provider

import com.egabruskiy.tasktimer.mvp.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class FireStoreProvider : RemoteDataProvider {


    var user = FirebaseAuth.getInstance().currentUser?.uid

    private val store by lazy {
        FirebaseFirestore.getInstance()
    }

    private val itemsReference by lazy {
        store.collection("users/users/$user/")
    }


    override fun getItems(): Observable<MutableList<Task>> {

        return Observable.create<MutableList<Task>> { emitter ->
            itemsReference
                    .addSnapshotListener { snapshot, e ->
                        e?.let {
                        } ?: let {
                            snapshot?.let {
                                val tasks = mutableListOf<Task>()
                                for (doc: QueryDocumentSnapshot in snapshot) {
                                    val note = doc.toObject(Task::class.java)
                                    tasks.add(note)

                                    emitter.onNext(tasks)
                                    emitter.onComplete()
                                }
                            }
                        }
                    }
        }.subscribeOn(Schedulers.io())
    }

    override fun saveItem(task: Task) {

            itemsReference.document(task.id)
                    .set(task)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener {
                    }
            }

    override fun deleteItem(task: Task) {
        itemsReference.document(task.id)
                .delete()
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                }
    }

}






