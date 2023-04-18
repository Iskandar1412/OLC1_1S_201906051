import React from 'react'
import { SectionIdEnum } from '../types';
import { IntroSection } from '../sections';
import { SectionContainer } from '../components';


const sections = [
  {
    sectionId: SectionIdEnum.intro,
    component: <IntroSection />
  },
  {
    sectionId: SectionIdEnum.about,
    component: <IntroSection />,
  },
  {
    sectionId: SectionIdEnum.skills,
    component: <IntroSection />,
  },
  {
    sectionId: SectionIdEnum.projects,
    component: <IntroSection />,
  },
];

const Inicio = () => {
  return (
    <>
    <div id='layoutSidenav_content'>
        <main>
            {sections.map(({component, sectionId}) => {
              return <SectionContainer sectionId={sectionId} key={sectionId}>
                {component}
              </SectionContainer>;
            })}
        </main>
    </div>
    </>
  )
};

export default Inicio;