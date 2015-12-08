/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
  private String tentaikhoan, matkhau, hoten, sdt, email;
  private EditText edttuade, edtngaytao, edtnoidung;
    private EditText edtmatkhau, edthoten, edtsdt, edtemail;
    private TextView edttentaikhoan;
    private Button doithongtintaikhoan, btnTaoTinTuc, btnCapNhatTinTuc;
    private ArrayList<DTTaiKhoan> listDTTK, list;
    private ArrayList<DTTinTuc> listTinTuc;
    private PTTaiKhoan PTTK;
    private ListView lvTinTuc;
    private customAdapterTinTuc adapterTinTuc;
    private customAdapterLichHoc adapterLichHoc;
    private DTTinTuc DTTT;
    private DTLichHoc DTLH;
    private PTTinTuc PTTT;
    //lich hoc
    private EditText edtngay, edtlop, edtphong, edtghichu, edtgio;
    private Button btnLuuLichHoc;
    private ArrayList<DTLichHoc> listDTLH;
    private ListView lvLichHoc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tabhots_activity);
    loadTab();
    khaibao();
    gangiatri();
    loadData();
      capnhattaikhoan();
    themTinTuc();
    themLichHoc();
    //xoaTinTuc();
    tinTucChiTiet();
  }
    public  void loadTab(){
    final TabHost tab = (TabHost) findViewById(R.id.tabHostmua);
    //setup
    tab.setup();
    TabHost.TabSpec spec;
    //tab1
    spec = tab.newTabSpec("t1");
    spec.setContent(R.id.tab_thongtin);
    spec.setIndicator("THÔNG TIN TÀI KHOẢN");
    tab.addTab(spec);
    //tab2
    spec = tab.newTabSpec("t2");
    spec.setContent(R.id.tab_lichhoc);
    spec.setIndicator("LỊCH HỌC");
    tab.addTab(spec);
    //tab3
    spec = tab.newTabSpec("t2");
    spec.setContent(R.id.tab_tintuc);
    spec.setIndicator("TIN TỨC");
    tab.addTab(spec);
    //Tab mặc định
    tab.setCurrentTab(0);
    //Sự kiện bên dưới comment này dùng khi mà chuyển tab nha Luân
    tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
      public void onTabChanged(String arg0) {
        String s ="";
        if(tab.getCurrentTab()==0){
          //chuyển tab 1
        }else{
          if(tab.getCurrentTab()==1){
            //chuyển tab 2
          }else {
            //chuyển tab 3
          }
        }
      }
    });
  }
  public void khaibao(){
    edttentaikhoan = (TextView) findViewById(R.id.edttentaikhoan);
    edtmatkhau = (EditText) findViewById(R.id.edtmatkhau);
    edthoten = (EditText) findViewById(R.id.edthoten);
    edtsdt = (EditText) findViewById(R.id.edtsdt);
    edtemail = (EditText) findViewById(R.id.edtemail);
    lvTinTuc = (ListView) findViewById(R.id.lvTinTuc);
    btnTaoTinTuc = (Button) findViewById(R.id.btnTaoTinTuc);
    edttuade = (EditText) findViewById(R.id.edtTuaDe);
    edtngaytao = (EditText) findViewById(R.id.edtNgayTao);
    edtnoidung = (EditText) findViewById(R.id.edtNoiDung);
    Intent intent = getIntent();
    tentaikhoan = intent.getStringExtra("tentaikhoan");
    matkhau = intent.getStringExtra("matkhau");
    hoten = intent.getStringExtra("hoten");
    sdt = intent.getStringExtra("sdt");
    email = intent.getStringExtra("email");

    //Ánh xá lịch học
      edtngay = (EditText) findViewById(R.id.edtngay);
      edtgio = (EditText) findViewById(R.id.edtgio);
      edtlop = (EditText) findViewById(R.id.edtlop);
      edtphong = (EditText) findViewById(R.id.edtphong);
      edtghichu = (EditText) findViewById(R.id.edtghichu);
      btnLuuLichHoc = (Button) findViewById(R.id.btnLuuLichHoc);
      lvLichHoc = (ListView) findViewById(R.id.listViewLichHoc);
  }
    public void capnhattaikhoan(){
        doithongtintaikhoan = (Button) findViewById(R.id.btnDoiTT);
        doithongtintaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("TaiKhoan");
                query.whereEqualTo("tentaikhoan", tentaikhoan);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e==null){
                            //ParseObject oj = new ParseObject("TaiKhoan");
                            //object.put("tentaikhoan", edttentaikhoan.getText().toString());
                            object.put("matkhau", edtmatkhau.getText().toString());
                            object.put("hoten", edthoten.getText().toString());
                            object.put("sdt", edtsdt.getText().toString());
                            object.put("email", edtemail.getText().toString());
                            object.saveInBackground();
                            Toast.makeText(MainActivity.this, "Cập nhật tài khoản thành công!",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Lỗi !",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
  public void gangiatri(){
    edttentaikhoan.setText(tentaikhoan);
    edtmatkhau.setText(matkhau);
    edthoten.setText(hoten);
    edtsdt.setText(sdt);
    edtemail.setText(email);
    //Lịch hoc
  }
  public void loadData(){
      loadLichHoc();
      loadTinTuc();
      loadLichHoc();
  }
  public  void loadTinTuc(){
      listTinTuc= new ArrayList<>();
      ParseQuery<ParseObject> parseNT = ParseQuery.getQuery("TinTuc");
      //parseNT.whereEqualTo("mataikhoan",mataikhoan);
      parseNT.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              listTinTuc.clear();
              if (e == null) {
                  for (ParseObject post : objects) {
                      final DTTinTuc DTTT = new DTTinTuc();
                      DTTT.idtuade = post.getObjectId();
                      DTTT.tuade = post.getString("tuade");
                      DTTT.ngaytao = post.getString("ngaytao");
                      DTTT.noidung = post.getString("noidung");
                      listTinTuc.add(DTTT);
                  }
                  //Load listView
                  adapterTinTuc = new customAdapterTinTuc(MainActivity.this, listTinTuc);
                  lvTinTuc.setAdapter(adapterTinTuc);
                  adapterTinTuc.notifyDataSetChanged();
              } else {
                  Toast.makeText(MainActivity.this, "Lỗi !",Toast.LENGTH_LONG).show();
              }
          }
      });
  }
    public void loadLichHoc(){
        listDTLH= new ArrayList<>();
        ParseQuery<ParseObject> parseLH = ParseQuery.getQuery("LichHoc");
        parseLH.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                listDTLH.clear();
                if (e == null) {
                    for (ParseObject post : objects) {
                        DTLH = new DTLichHoc();
                        DTLH.idlichhoc = post.getObjectId();
                        DTLH.ngay = post.getString("ngay");
                        DTLH.gio = post.getString("gio");
                        DTLH.lop = post.getString("lop");
                        DTLH.phong = post.getString("phong");
                        listDTLH.add(DTLH);
                    }
                    //Load listView
                    adapterLichHoc = new customAdapterLichHoc(MainActivity.this, listDTLH);
                    lvLichHoc.setAdapter(adapterLichHoc);
                    adapterLichHoc.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
  public void themTinTuc(){
      btnTaoTinTuc.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ParseObject oj = new ParseObject("TinTuc");
              //oj.put("idtuade", DTTT.idtuade);
              oj.put("tuade", edttuade.getText().toString());
              oj.put("ngaytao", edtngaytao.getText().toString());
              oj.put("noidung", edtnoidung.getText().toString());
              oj.saveInBackground(new SaveCallback() {
                  @Override
                  public void done(ParseException e) {
                      if(e==null){
                          Toast.makeText(MainActivity.this, "Tạo tin tức thành công", Toast.LENGTH_LONG).show();
                      }else {
                          Toast.makeText(MainActivity.this, "Tạo tin tức lỗi", Toast.LENGTH_LONG).show();
                      }
                  }
              });
              loadTinTuc();
          }
      });
      loadData();
  }
  public void themLichHoc(){
      btnLuuLichHoc.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ParseObject oj = new ParseObject("LichHoc");
              //oj.put("idtuade", DTTT.idtuade);
              oj.put("ngay", edtngay.getText().toString());
              oj.put("gio", edtgio.getText().toString());
              oj.put("lop", edtlop.getText().toString());
              oj.put("phong", edtphong.getText().toString());
              oj.put("ghichu", edtghichu.getText().toString());
              oj.saveInBackground(new SaveCallback() {
                  @Override
                  public void done(ParseException e) {
                      if(e==null){
                          Toast.makeText(MainActivity.this, "Tạo lịch học thành công", Toast.LENGTH_LONG).show();
                      }else {
                          Toast.makeText(MainActivity.this, "Tạo lịch học lỗi", Toast.LENGTH_LONG).show();
                      }
                  }
              });
              loadLichHoc();
          }
      });
  }
    private void xoaTinTuc() {
        lvTinTuc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                //Toast.makeText(MainActivity.this, "Vị trí "+ pos,Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("TinTuc");
                        query.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, com.parse.ParseException e) {
                                if (e == null) {
                                    try {
                                        object.delete();
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            loadData();
                                            Toast.makeText(MainActivity.this, "Đã xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });
    }
    public void tinTucChiTiet(){
        lvTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DTTinTuc tintuc = listTinTuc.get(position);
                Intent intent = new Intent(MainActivity.this, TinTucChiTiet_Activity.class);
                intent.putExtra("tuade", tintuc.tuade);
                intent.putExtra("ngaytao", tintuc.ngaytao);
                intent.putExtra("noidung", tintuc.noidung);
                startActivity(intent);
            }
        });
    }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    switch (id) {
      case R.id.action_refresh: {
          loadData();
        break;
      }
      case R.id.action_new: {
      }
    }

    return super.onOptionsItemSelected(item);
  }

}
