import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class WindowsExample extends Panel {
   
   Button searchB, sendB, cancelB;
   TextField receiveTF, attachTF, titleTF;
   TextArea contentsTF;
   Label receiveL, attachL, titleL, emptyL;
   
   GridBagLayout gridBagLayout;
   GridBagConstraints gridBagConstraints;
   
   Panel buttonPanel;
   
   public WindowsExample() {
      searchB = new Button("찾  기");
      sendB = new Button("send");
      cancelB = new Button("cancel");
      receiveTF = new TextField();
      attachTF = new TextField();
      titleTF = new TextField();
      contentsTF = new TextArea();
      receiveL = new Label("받는사람");
      attachL = new Label("첨부파일");
      titleL = new Label("제        목", Label.LEFT);
      
      emptyL = new Label("        ");
      
      buttonPanel = new Panel();
      gridBagLayout = new GridBagLayout();
      gridBagConstraints = new GridBagConstraints();
      
   }
   public void setContents() {
      setLayout(gridBagLayout);
      add(receiveL,   0, 0, 1, 1, 0, 1);
      add(receiveTF,  1, 0, 2, 1, 1, 1);
      add(emptyL,     3, 0, 1, 1, 1, 1);
      add(attachL,    0, 1, 1, 1, 0, 1);
      add(attachTF,   1, 1, 1, 1, 1, 1);
      add(searchB,    2, 1, 1, 1, 0, 1);
      add(titleL,     0, 2, 1, 1, 0, 1);
      add(titleTF,    1, 2, 5, 1, 0, 1);
      add(contentsTF, 0, 3, 5, 5, 0, 1);
      
      
      buttonPanel.add(sendB);
      buttonPanel.add(cancelB);
      
      add(buttonPanel,0, 4, 5, 1, 0, 1);
//      
      
   }

   // 버튼을 부착할때마다 설정을 해줘야하므로 반복적 업무 -> 메소드로 만들어서 한꺼번에 처리
   private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
      gridBagConstraints.gridx = gridx;
      gridBagConstraints.gridy = gridy; // 변함 없음 -> 옆으로 이동하니까 
      gridBagConstraints.gridwidth = gridwidth;
      gridBagConstraints.gridheight = gridheight;
      
      gridBagConstraints.weightx = weightx; // 가중치 필수요소
      gridBagConstraints.weighty = weighty;
       gridBagConstraints.fill = GridBagConstraints.BOTH; // 격자의 가로 부분을 꽉 채워 -> HORIZONTAL 많이 사용 // VERTICAL은 가중치가 있어야 작동함
                                                 // Default NONE;
      // 버튼의 사이사이에 여백을 주고 싶어
      // gridBagConstraints.insets = new Insets(5, 5, 5, 5);
      
      gridBagLayout.setConstraints(component, gridBagConstraints); // 위에 4가지 설정을 바탕으로 button2에 제약사항을 적용해주세요
      add(component);
   }
   
   public static void main(String[] args) {
      Frame frame = new Frame("화면실습"); // 생성은 이 커맨드 라인에서 실행(부착은 UserFrame 클래스에서 진행)
      WindowsExample panel = new WindowsExample();
      
      panel.setContents();
      frame.add(panel);
//      frame.pack();
      frame.setSize(800, 500);
      frame.setVisible(true);
   }
}