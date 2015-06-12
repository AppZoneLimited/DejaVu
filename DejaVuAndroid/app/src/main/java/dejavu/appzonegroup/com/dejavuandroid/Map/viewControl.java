package dejavu.appzonegroup.com.dejavuandroid.Map;

import android.view.View;

import dejavu.appzonegroup.com.dejavuandroid.PageRenderer.UI_Object;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Button;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_CheckBox;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DropDown;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_FileChooser;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Label;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_List;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_ReadOnly;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextArea;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextField;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_YesNo;

/**
 * Created by CrowdStar on 3/12/2015.
 */
public class viewControl {

    static public View getViewByType(UI_Object ui_object) {
        switch (ui_object.getType()) {
            case UI_Type.DJV_SingleLineField:
                DJV_TextField djv_textField = new DJV_TextField(ui_object.getContext(), null, 0, ui_object);
                return djv_textField;
            case UI_Type.DJV_SingleSelection:
                return new DJV_DropDown(ui_object.getContext(), ui_object);
            case UI_Type.DJV_MultilineField:
                DJV_TextArea djv_textArea = new DJV_TextArea(ui_object.getContext(), null, 0, ui_object);
                return djv_textArea;
            case UI_Type.DJV_ReadOnly:
                DJV_ReadOnly djv_readOnly = new DJV_ReadOnly(ui_object.getContext(), null, 0, ui_object);
                return djv_readOnly;
            case UI_Type.DJV_Button:
                DJV_Button djv_button = new DJV_Button(ui_object.getContext(), null, 0, ui_object);
                return djv_button;
            case UI_Type.DJV_CheckBox:
                DJV_CheckBox djv_checkBox = new DJV_CheckBox(ui_object.getContext(), null, 0, ui_object);
                return djv_checkBox;
            case UI_Type.DJV_Label:
                return new DJV_Label(ui_object.getContext(), null, 0, ui_object);
            case UI_Type.DJV_YesNO:
                return new DJV_YesNo(ui_object.getContext(), ui_object);
            case UI_Type.DJV_DropDown:
                return new DJV_DropDown(ui_object.getContext(), ui_object);
            case UI_Type.DJV_List:
                return new DJV_List(ui_object.getContext(), ui_object);
            case UI_Type.DJV_DateField:
                return new DJV_DatePicker(ui_object.getContext(), ui_object);
            case UI_Type.DJV_FileUpload:
                DJV_FileChooser djv_fileChooser = new DJV_FileChooser(ui_object.getContext(), null, 0, ui_object);
                return djv_fileChooser;
            default:
                return new View(ui_object.getContext());
        }
    }


}
