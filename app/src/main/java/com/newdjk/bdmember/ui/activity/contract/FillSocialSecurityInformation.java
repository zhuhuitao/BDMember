package com.newdjk.bdmember.ui.activity.contract;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.DocQualificationEntity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.bean.PaintListEntity;
import com.newdjk.bdmember.bean.PatientRegImgImgsInfo;
import com.newdjk.bdmember.bean.PatientRegImgListInfo;
import com.newdjk.bdmember.bean.PicturePathEntity;
import com.newdjk.bdmember.bean.ResponseEntity;
import com.newdjk.bdmember.bean.SocialSecurityInfo;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.GlidUtil;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.PermissionUtil;
import com.newdjk.bdmember.utils.UploadPictureUitl;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.widget.SelectedPictureDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

import static com.newdjk.bdmember.utils.PermissionUtil.INSTANCE;

public class FillSocialSecurityInformation extends BasicActivity implements SelectedPictureDialog.OnPictrueClickListener, BaseCallback {
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.relat_titlebar)
    RelativeLayout relatTitlebar;
    @BindView(R.id.liear_titlebar)
    LinearLayout liearTitlebar;
    @BindView(R.id.tv_paint_name)
    TextView tvPaintName;
    @BindView(R.id.tv_social_security_num)
    TextView tvSocialSecurityNum;
    @BindView(R.id.sw_button)
    Switch swButton;
    @BindView(R.id.et_social_security_num)
    EditText etSocialSecurityNum;
    @BindView(R.id.iv_social_security_card)
    ImageView ivSocialSecurityCard;
    @BindView(R.id.bt_confirm_submit)
    AppCompatButton btConfirmSubmit;
    private PaintListEntity.DataBean mPaintInfo;
    private List<DocQualificationEntity.DataBean> mList;
    private SocialSecurityInfo.DataBean mSocialSecurityInfo;
    private SelectedPictureDialog mSelectedPictureDialog;
    private String mPicturePath;
    private final int mCameraRequestCode = 01;
    private final int mAlbumRequestCode = 02;
    private String mSavePath;
    private String mSocialSecurityNumber;
    private Gson mGson;

    @Override
    protected int initViewResId() {
        return R.layout.activity_fill_social_security_info;
    }

    @Override
    protected void initView() {
        initTitle(getString(R.string.socialSecurityInfo)).setLeftImage(R.mipmap.head_back_n).setLeftOnClickListener(v ->
                finish()
        ).setTitleBgRes(R.color.white);
        mPaintInfo = getIntent().getParcelableExtra("socialSecurity");
        UploadPictureUitl.INSTANCE.setCallback(this);
        mGson = new Gson();
    }

    private void showPaintInfo() {
        if (mPaintInfo == null) {
            return;
        }
        tvPaintName.setText(mPaintInfo.getPatientName() == null ? "" : mPaintInfo.getPatientName());

        if (mSocialSecurityInfo == null) return;
        etSocialSecurityNum.setText(mSocialSecurityInfo.getSocialNum() == null ? "" : mSocialSecurityInfo.getSocialNum());
        GlidUtil.loadImage(this, mSocialSecurityInfo.getImgPath(), ivSocialSecurityCard);
        mSavePath = mSocialSecurityInfo.getImgPath();
    }

    @Override
    protected void initListener() {
        ivSocialSecurityCard.setOnClickListener(v -> upLoadImage());

        btConfirmSubmit.setOnClickListener(v -> submitSocialSecurityInfo());

        btConfirmSubmit.setOnClickListener(v->submitInfo());
    }

    private void submitInfo() {

    }

    private void submitSocialSecurityInfo() {
        mSocialSecurityNumber = etSocialSecurityNum.getText().toString().trim();
        if (TextUtils.isEmpty(mSocialSecurityNumber)) {
            toast(getString(R.string.pleaseFillSocialSecurityNum));
            return;
        }

        PatientRegImgImgsInfo image = new PatientRegImgImgsInfo();
        image.setCertNO(mSocialSecurityNumber);
        image.setImgType(3);
        image.setImgPath(mSavePath);
        PatientRegImgListInfo listInfo = new PatientRegImgListInfo();
        listInfo.setPatientId(mPaintInfo.getPatientId());
        List<PatientRegImgImgsInfo> list = new ArrayList<>();
        list.add(image);
        listInfo.setPatientRegImgImgs(list);
        String json = mGson.toJson(listInfo);
        loading(true);
        mMyOkhttp.post().url(HttpUrl.PatientImagSave).jsonParams(json).tag(this).enqueue(new GsonResponseHandler<Entity>() {
            @Override
            public void onSuccess(int statusCode, Entity entity) {
                LoadDialog.clear();
                if (entity.getCode() == 0) {
                    toast(getString(R.string.submitSuccess));
                    EventBus.getDefault().postSticky(mSocialSecurityNumber);
                    FillSocialSecurityInformation.this.finish();
                } else {
                    toast(entity.getMessage());
                }

            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                LoadDialog.clear();
                CommonMethod.requestError(statusCode, errorMsg);
            }
        });
    }

    private void upLoadImage() {
        if (INSTANCE.checkPermissionStorage(this)) {
            mSelectedPictureDialog = new SelectedPictureDialog(this);
            mSelectedPictureDialog.setmOnPictrueClickListener(this);
            mSelectedPictureDialog.show();
        }
    }

    @Override
    protected void initData() {
        obtainSocialSecurity();

    }

    private void obtainSocialSecurity() {
        String url = HttpUrl.QueryPatientSocialByPatientId + "?PatientId=" + mPaintInfo.getPatientId();

        loading(true);
        mMyOkhttp.get().url(url).tag(this).enqueue(new GsonResponseHandler<SocialSecurityInfo>() {
            @Override
            public void onSuccess(int statusCode, SocialSecurityInfo response) {
                LoadDialog.clear();
                if (response.getCode() == 0) {
                    if (response.getData() != null) {
                        mSocialSecurityInfo = response.getData();
                        showPaintInfo();
                    }
                }

            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {

            }


        });
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtil.REQUEST_CODE:
                for (int ret : grantResults) {
                    if (PackageManager.PERMISSION_GRANTED != ret) {
                        Toast.makeText(this, getString(R.string.noPermissionTips), Toast.LENGTH_SHORT).show();
                        //  addLogMessage("用户没有允许需要的权限，使用可能会受到限制！");
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCamera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String f = System.currentTimeMillis() + ".jpg";
        mPicturePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + f;
        File file = new File(mPicturePath);
        file.getParentFile().mkdirs();
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(mContext, "com.xqd.struggle.member.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }

        //添加权限
        openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        this.startActivityForResult(openCameraIntent, mCameraRequestCode);
    }

    @Override
    public void onALbum() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(intent, mAlbumRequestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case mAlbumRequestCode:
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径

                        UploadPictureUitl.INSTANCE.uploadPicture(path);
                        GlidUtil.loadImage(this, path, ivSocialSecurityCard);
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case mCameraRequestCode:
                    GlidUtil.loadImage(this, mPicturePath, ivSocialSecurityCard);
                    UploadPictureUitl.INSTANCE.uploadPicture(mPicturePath);
                    break;
            }
        }
    }

    @Override
    public void success(Object o) {
        ResponseEntity<PicturePathEntity> entity = (ResponseEntity<PicturePathEntity>) o;
        if (entity.getCode() == 0) {
            mSavePath = entity.getData().getSavePath();
        }
    }

    @Override
    public void failed(int errorCode, String errorMsg) {
        CommonMethod.requestError(errorCode, errorMsg);
    }
}
