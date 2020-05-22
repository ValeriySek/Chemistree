package com.selflearning.chemistree.activities.trening;

import androidx.annotation.Nullable;

//  Проверка данных состояния формы входа.

class TrainingLoginFromState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean isDataValid;

    public TrainingLoginFromState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    public TrainingLoginFromState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
