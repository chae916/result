package hotel;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="ResultProcessing_table")
public class ResultProcessing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String userid;
    private String date;
    private Long regId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        ResultEnded resultEnded = new ResultEnded();
        BeanUtils.copyProperties(this, resultEnded);
        resultEnded.publish();


    }

    @PostUpdate
    public void onPostUpdate(){
        ResultChanged resultChanged = new ResultChanged();
        BeanUtils.copyProperties(this, resultChanged);
        resultChanged.publish();


    }

    @PostRemove
    public void onPostRemove(){
        ResultCanceled resultCanceled = new ResultCanceled();
        BeanUtils.copyProperties(this, resultCanceled);
        resultCanceled.publish();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
