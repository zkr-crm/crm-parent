package cn.com.zsyk.crm.manage.bom;

import java.io.Serializable;
import java.util.List;

public class SendMsg implements Serializable {

    private String contents;//短信内容
    private List<String> phoneList;//手机号

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

}
