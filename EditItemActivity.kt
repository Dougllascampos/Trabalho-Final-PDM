package com.seuusuario.appdoacao

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.seuusuario.appdoacao.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private var currentItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore
        auth = Firebase.auth

        currentItemId = intent.getStringExtra("ITEM_ID")

        if (currentItemId == null) {
            binding.tvTitle.text = "Adicionar Novo Item"
            binding.btnDelete.visibility = View.GONE
        } else {
            binding.tvTitle.text = "Editar Item"
            binding.btnSave.text = "Atualizar"
            binding.btnDelete.visibility = View.VISIBLE
            loadItemData(currentItemId!!)
        }

        binding.btnSave.setOnClickListener {
            saveItem()
        }
        binding.btnDelete.setOnClickListener {
            deleteItem()
        }
    }

    private fun loadItemData(itemId: String) {
        db.collection("items").document(itemId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val item = document.toObject(Item::class.java)
                    if (item != null) {
                        binding.etItemTitle.setText(item.title)
                        binding.etItemDescription.setText(item.description)
                        binding.etItemCategory.setText(item.category)
                    }
                } else {
                    Toast.makeText(this, "Item não encontrado.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao carregar item.", Toast.LENGTH_SHORT).show()
                finish()
            }
    }

    private fun saveItem() {
        val title = binding.etItemTitle.text.toString()
        val description = binding.etItemDescription.text.toString()
        val category = binding.etItemCategory.text.toString()
        val userId = auth.currentUser?.uid

        if (title.isEmpty() || description.isEmpty() || category.isEmpty() || userId == null) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val itemMap = hashMapOf(
            "title" to title,
            "description" to description,
            "category" to category,
            "userId" to userId
        )

        if (currentItemId == null) {
            db.collection("items")
                .add(itemMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Item salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao salvar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            db.collection("items").document(currentItemId!!)
                .set(itemMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Item atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao atualizar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun deleteItem() {
        if (currentItemId != null) {
            db.collection("items").document(currentItemId!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Item removido.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao remover: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
