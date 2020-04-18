package com.el.quees.Interface;

public interface Main_interface  {

    interface Main_modelo{
        void executeRequest(String request);
        void executeDataUser();
    }

    interface Main_presentador{
        void sendRequest(String request);
        void getDataUsers();
        void returnRequest();
    }

    interface Main_vista{
        void showRequest();
    }

}
