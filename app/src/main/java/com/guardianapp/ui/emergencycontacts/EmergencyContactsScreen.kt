package com.guardianapp.ui.emergencycontacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

data class EmergencyContact(
    val name: String,
    val relation: String,
    val phoneNumber: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactScreen(navController: NavController) {
    var contactList by remember { mutableStateOf(listOf<EmergencyContact>()) }
    var showAddDialog by remember { mutableStateOf(false) }
    var editIndex by remember { mutableStateOf(-1) }

    // Dialog state variables
    var name by remember { mutableStateOf("") }
    var relation by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top App Bar with back button
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 28.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    // ... existing code ...
                    item {
                        Text(
                            text = "Emergency Contact",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Store and manage trusted emergency numbers",
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    items(contactList.size) { index ->
                        val contact = contactList[index]
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .shadow(8.dp, RoundedCornerShape(12.dp)),
                            elevation = CardDefaults.cardElevation(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            // ... existing code ...
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row {
                                    Text("Name: ", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text(contact.name, color = Color(0xFF87CEFA), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                }
                                Row {
                                    Text("Relation: ", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text(contact.relation, color = Color(0xFF87CEFA), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                }
                                Row {
                                    Text("Phone Number: ", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text(contact.phoneNumber, color = Color(0xFF87CEFA), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                }

                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    IconButton(onClick = {
                                        editIndex = index
                                        name = contact.name
                                        relation = contact.relation
                                        phoneNumber = contact.phoneNumber
                                        showAddDialog = true
                                    }) {
                                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Blue)
                                    }
                                    IconButton(onClick = {
                                        contactList = contactList.toMutableList().also { it.removeAt(index) }
                                    }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                                    }
                                }
                            }
                        }
                    }
                }

                FloatingActionButton(
                    onClick = {
                        editIndex = -1
                        name = ""
                        relation = ""
                        phoneNumber = ""
                        showAddDialog = true
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    containerColor = Color(0xFF87CEFA)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Contact")
                }
            }
        }

        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text(text = if (editIndex == -1) "Add Contact" else "Edit Contact") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = relation,
                            onValueChange = { relation = it },
                            label = { Text("Relation") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text("Phone Number") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val newContact = EmergencyContact(name, relation, phoneNumber)
                            contactList = if (editIndex == -1) {
                                contactList + newContact
                            } else {
                                contactList.toMutableList().apply {
                                    set(editIndex, newContact)
                                }
                            }
                            showAddDialog = false
                        }
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}