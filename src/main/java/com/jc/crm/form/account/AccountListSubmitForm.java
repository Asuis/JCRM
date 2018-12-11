package com.jc.crm.form.account;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountListSubmitForm {
    @Size(min = 1)
    @Valid
    List<RegisterForm> data;

    public List<RegisterForm> getData() {
        return data;
    }

    public void setData(List<RegisterForm> data) {
        this.data = data;
    }
}
