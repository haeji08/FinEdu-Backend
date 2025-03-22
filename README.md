# FinEdu-Backend
Cloud System 2024-2 team project: Cloud native educational finance platform | Backend Repository

## Team
| 박해지 | 박유진 | 유채민 |
| --- | --- | --- |
| <p align="center"><img src="https://i.postimg.cc/NFWsq98V/image.jpg" width="150" height="150"/></p>| <p align="center"><img src="https://i.postimg.cc/HkSMcHmL/img-character01.png" width="125" height="150"/></p> | <p align="center"><img src="https://i.postimg.cc/HkSMcHmL/img-character01.png" width="125" height="150"/></p> |
| 숙명여자대학교 컴퓨터과학과 4학년 | 숙명여자대학교 컴퓨터과학과 4학년 | 숙명여자대학교 컴퓨터과학과 4학년 |

## Background
With the increasing complexity of financial markets and products, financial literacy has become essential, especially among younger generations. However, the overwhelming flow of financial news makes it difficult for individuals to keep up and understand key information.

## Details
Our service addresses these social challenges by leveraging advanced cloud technology and AI-powered news summarization to provide an easy-to-use financial learning platform. 

### Key features
1. **Real-time News Analysis Powered by ChatGPT**
   Simplifies complex financial information into an easily digestible format for users.

3. **Auto-scaling with Kubernetes**
   Ensures uninterrupted service, even during periods of market volatility, by utilizing Kubernetes' auto-scaling capabilities.

### Service UI
| News Summarization | Quiz | 
| --- | --- | 
| <img src="https://i.postimg.cc/sxxJ7py8/2025-03-22-5-28-10.png" width="270"/> | <img src="https://i.postimg.cc/P5yz33xc/Kakao-Talk-Photo-2025-03-22-17-30-59.png" width="300"/> | 


### Operation Scenario
1. 주식 시장 급변동으로 인한 뉴스 폭증 대응 ➡️ 자동으로 요약 Pod 수를 늘려 요청을 빠르게 처리
2. 사용자의 요청 처리와 부하 분산 (로드밸런싱) ➡️ 트래픽을 Pod들에 균등하게 분산하여 처리 속도 유지
3. 데이터 백업 및 볼륨 관리 (데이터 안전성) ➡️ MySQL 데이터를 PersistentVolume에 저장하여 컨테이너 재시작이나 장애 발생 시에도 데이터 손실 없이 복구

## Structure Of Project
```
fineduai/ 
├── frontend/ # Frontend repo 
├── crawler/ # Crawler repo 
├── backend/ # Backend repo 
│ ├── common/ # 공동 모듈
│ ├── summarizer/ # 요약본 모듈
│ ├── quiz/ # 퀴즈 모듈
└── kubernetes/ # Kubernetes manifests 
  ├── mysql/ 
  │ ├── mysql-pv.yaml 
  │ ├── mysql-pvc.yaml 
  │ ├── mysql-deployment.yaml 
  │ └── mysql-service.yaml 
  ├── crawler/ 
  │ ├── crawler-deployment.yaml 
  │ ├── crawler-service.yaml 
  │ └── crawler-cronjob.yaml 
  ├── summarizer/ 
  │ ├── summarizer-deployment.yaml 
  │ └── summarizer-service.yaml 
  ├── quiz/ 
  │ ├── quiz-deployment.yaml 
  │ └── quiz-service.yaml 
  └── frontend/ 
    ├── frontend-deployment.yaml 
    └── frontend-service.yaml`
```

## Stacks
### Environment
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

### Development
<img src=https://camo.githubusercontent.com/c5c6f5ba41163a05ef0c9aa47053749f7b2da2edaa4df9002af8345adcf8a9f0/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e67626f6f742d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67626f6f74266c6f676f436f6c6f723d7768697465> ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

### Infra
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Kubernetes](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white)

### Communications
<img src="https://camo.githubusercontent.com/9a590df5c8f036b6e902a198e3fcc4309216fcdb58967888f250d92ace816c02/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f476f6f676c654d6565742d3030383937423f7374796c653d666f722d7468652d6261646765266c6f676f3d476f6f676c652532304d656574266c6f676f436f6c6f723d7768697465"/> ![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
