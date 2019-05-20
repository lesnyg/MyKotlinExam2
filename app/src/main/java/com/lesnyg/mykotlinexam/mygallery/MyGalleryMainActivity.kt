package com.lesnyg.mykotlinexam.mygallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lesnyg.mykotlinexam.R
import com.lesnyg.mykotlinexam.databinding.ItemPhotoBinding
import com.lesnyg.mykotlinexam.logd
import com.lesnyg.mykotlinexam.toast
import kotlinx.android.synthetic.main.activity_my_gallery_main.*
import kotlin.concurrent.timer

class MyGalleryMainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MyGalleryMainActivity::class.java.simpleName
        const val REQUEST_READ_EXTERNAL_STORAGE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gallery_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //이전에 이미 권한이 거부되었을 때 설명
                AlertFragment {
                    //권한요청 다시한번 함
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_READ_EXTERNAL_STORAGE
                    )
                }.show(supportFragmentManager, "dialog")
            } else {
                //권한요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERNAL_STORAGE
                )
            }
        } else {
            getAllPhotos();
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 허용됨
                    getAllPhotos()
                } else {
                    //권한거부
                    toast("권한 거부 됨")
                }
            }
        }
    }


    private fun getAllPhotos() {
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
        )

        val items = arrayListOf<Photo>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                logd(uri.toString())
                items.add(Photo(uri))
            }
            cursor.close()
        }
        val adapter = PhotoAdapter()
        adapter.items = items
        adapter.notifyDataSetChanged()

        view_pager.adapter = adapter

        timer(period = 3000) {
            runOnUiThread {
                if (view_pager.currentItem < adapter.itemCount - 1) {
                    view_pager.currentItem = view_pager.currentItem + 1
                } else {
                    view_pager.currentItem = 0
                }
            }
        }
    }
}

//Model
class Photo(val uri: String)

//Adapter
class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    var items = arrayListOf<Photo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        val binding = ItemPhotoBinding.bind(view)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding.photo = items[position]
    }

    class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}
