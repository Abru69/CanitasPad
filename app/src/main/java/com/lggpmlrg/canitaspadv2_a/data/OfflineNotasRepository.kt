package com.lggpmlrg.canitaspadv2_a.data

import com.lggpmlrg.canitaspadv2_a.model.Nota
import kotlinx.coroutines.flow.Flow

class OfflineNotasRepository(private val notaDAO: NotaDAO) : NotasRepository{
    override fun getAllItemsStream(): Flow<List<Nota>> = notaDAO.getAllItems()

    override fun getItemStream(id: Int): Flow<Nota?> = notaDAO.getItem(id)

    override suspend fun insertItem(nota: Nota) = notaDAO.insert(nota)

    override suspend fun deleteItem(nota: Nota) = notaDAO.delete(nota)

    override suspend fun updateItem(nota: Nota) = notaDAO.update(nota)
}