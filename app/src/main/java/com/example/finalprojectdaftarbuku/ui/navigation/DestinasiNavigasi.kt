package com.example.finalprojectdaftarbuku.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

//Destinasi Buku
object DestinasiHome: DestinasiNavigasi{
    override val route ="home"
    override val titleRes = "Home"
}

object DestinasiStatus: DestinasiNavigasi{
    override val route ="status_buku"
    override val titleRes = "Status"
}

object DestinsaiInsertBuku: DestinasiNavigasi{
    override val route ="tambah_buku"
    override val titleRes = "Tambah Buku"
}

object DestinasiDetailBuku: DestinasiNavigasi{
    override val route = "detail_buku"
    override val titleRes = "Detail Buku"
    const val IDB = "idBuku"
    val routesWithArg = "$route/{$IDB}"
}

object DestinasiUpdateBuku: DestinasiNavigasi{
    override val route = "edit_buku"
    override val titleRes = "Edit Buku"
    const val IDB = "idBuku"
    val routesWithArg = "$route/{$IDB}"
}
//Destinasi Kategori
object DestinasiDaftarKategori: DestinasiNavigasi{
    override val route = "kategori"
    override val titleRes = "Kategori"
    const val IDK = "idKategori"
    val routesWithArg = "$route/{$IDK}"
}

object DestinasiDetailKategori: DestinasiNavigasi{
    override val route = "detail_kategori"
    override val titleRes = "Detail Kategori"
    const val IDK = "idKategori"
    val routesWithArg = "$route/{$IDK}"
}

object DestinasiTambahKategori: DestinasiNavigasi{
    override val route = "tambah_kategori"
    override val titleRes = "Tambah Kategori"
}

object DestinasiUpdateKategori: DestinasiNavigasi{
    override val route = "edit_kategori"
    override val titleRes = "Edit Kategori"
    const val IDK = "idKategori"
    val routesWithArg = "$route/{$IDK}"
}

//Destinasi Penerbit
object DestinasiDaftarPenerbit: DestinasiNavigasi{
    override val route = "penerbit"
    override val titleRes = "Penerbit"
}

object DestinasiDetailPenerbit: DestinasiNavigasi{
    override val route = "detail_penerbit"
    override val titleRes = "Detail Penerbit"
    const val IDPN = "idPenerbit"
    val routesWithArg = "$route/{$IDPN}"
}

object DestinasiTambahPenerbit: DestinasiNavigasi{
    override val route = "tambah_penerbit"
    override val titleRes = "Tambah Penerbit"
}

object DestinasiUpdatePenerbit: DestinasiNavigasi{
    override val route = "edit_penerbit"
    override val titleRes = "Edit Penerbit"
    const val IDPN = "idPenerbit"
    val routesWithArg = "$route/{$IDPN}"
}

//Destinasi Penulis
object DestinasiDaftarPenulis: DestinasiNavigasi{
    override val route = "penulis"
    override val titleRes = "Penulis"
}

object DestinasiDetailPenulis: DestinasiNavigasi{
    override val route = "detail_penulis"
    override val titleRes = "Detaiil Penulis"
    const val IDPS = "idPenulis"
    val routesWithArg = "$route/{$IDPS}"
}

object DestinasiTambahPenulis: DestinasiNavigasi{
    override val route = "tambah_penulis"
    override val titleRes = "Tambah Penulis"
}

object DestinasiUpdatePenulis: DestinasiNavigasi{
    override val route = "edit_penulis"
    override val titleRes = "Edit Penulis"
    const val IDPS = "idPenulis"
    val routesWithArg = "$route/{$IDPS}"
}
