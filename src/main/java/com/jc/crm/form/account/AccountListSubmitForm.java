package com.jc.crm.form.account;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountListSubmitForm {
    @Size(min = 1)
    @Valid
    List<AccountListSubmitForm> data;
}
