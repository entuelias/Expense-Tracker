package com.guardianapp.ui.medicalinfo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class MedicalEntry(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalInfoScreen(navController: NavController) {
    var medicalList by remember { mutableStateOf(listOf<MedicalEntry>()) }
    var showDialog by remember { mutableStateOf(false) }
    var editIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Medical Info") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFADD8E6),
                    contentColor = Color.White
                )
            ) {
                Text("+ Add New", fontSize = 24.sp)
            }

            if (medicalList.isEmpty()) {
                Text(
                    text = "No medical info added yet. Add your medications, allergies, or medical conditions so you have quick access when needed.\nClick '+ Add New' to create one.",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(medicalList.size) { index ->
                    val entry = medicalList[index]
                    MedicalEntryCard(
                        title = entry.title,
                        description = entry.description,
                        onEdit = {
                            editIndex = index
                            showDialog = true
                        },
                        onDelete = {
                            medicalList = medicalList.toMutableList().also { it.removeAt(index) }
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        val currentEntry = medicalList.getOrNull(editIndex)
        MedicalEntryDialog(
            initialTitle = currentEntry?.title ?: "",
            initialDescription = currentEntry?.description ?: "",
            onSave = { title, description ->
                medicalList = if (editIndex >= 0) {
                    medicalList.toMutableList().apply {
                        this[editIndex] = MedicalEntry(title, description)
                    }
                } else {
                    medicalList + MedicalEntry(title, description)
                }
                showDialog = false
                editIndex = -1
            },
            onCancel = {
                showDialog = false
                editIndex = -1
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalEntryCard(title: String, description: String, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 16.sp, color = Color.DarkGray)

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Blue)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalEntryDialog(
    initialTitle: String,
    initialDescription: String,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(initialTitle) }
    var description by remember { mutableStateOf(initialDescription) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(if (initialTitle.isEmpty()) "Add Medical Info" else "Edit Medical Info") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isNotBlank()) {
                        onSave(title, description)
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}