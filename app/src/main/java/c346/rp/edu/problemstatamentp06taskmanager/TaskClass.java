package c346.rp.edu.problemstatamentp06taskmanager;

import java.io.Serializable;

public class TaskClass  implements Serializable {

        private int _id;
        private String title;
        private String description;


        public TaskClass(String description, String title ){

            this.title = title;
            this.description = description;


        }

        public int get_id() {
            return _id;
        }

        public String getDescription() {
            return description;
        }


        public String getTitle() {
            return title;
        }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
